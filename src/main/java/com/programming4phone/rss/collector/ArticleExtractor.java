package com.programming4phone.rss.collector;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.programming4phone.rss.collector.admin.RssFeedException;
import com.programming4phone.rss.collector.entity.Article;
import com.rometools.rome.feed.module.DCModuleImpl;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Component
public class ArticleExtractor implements Function<String,List<Article>>{

	@Override
	public List<Article> apply(String rssUrl) {

		List<Article> articles = new ArrayList<Article>();
		SyndFeedInput feedInput = new SyndFeedInput();
        try {
			SyndFeed feed = feedInput.build(new XmlReader(new URL(rssUrl)));
			feed.getEntries().stream()
				.map(
						(syndEntry) -> new Article()
							.setTitle(syndEntry.getTitle())
							.setAuthor(getAuthor(syndEntry.getModules()))
							.setDescription(syndEntry.getDescription().getValue())
							.setPubDate(syndEntry.getPublishedDate())
							.setArticleLink(syndEntry.getLink())
							.setImageLink(getEnclosureUrl(syndEntry.getEnclosures()))
				)
				.forEach((article) -> articles.add(article));
        
        } 
        catch (IllegalArgumentException | FeedException | IOException e) {
			throw new RssFeedException("Unable to access RSS feed:" + rssUrl);
		}
		return articles;
	}
	
	
	private String getAuthor(List<Module> modules){
		StringBuilder sb = new StringBuilder();
		modules.stream().findFirst().ifPresent(m->
			getCreator(((DCModuleImpl)m).getCreators()).ifPresent(s->sb.append(s)));
		return sb.length()>0 ? sb.toString() : null;
	}
	
	private Optional<String> getCreator(List<String> creators){
		StringBuilder sb = new StringBuilder();
		creators.stream().findFirst().ifPresent(s->sb.append(s));
		return sb.length()>0 ? Optional.of(sb.toString()) : Optional.empty();
	}

	
	private String getEnclosureUrl (List<SyndEnclosure> enclosures){
		StringBuilder sb = new StringBuilder();
		enclosures.stream().findFirst().ifPresent(e->sb.append(e.getUrl()));
		return sb.length()>0 ? sb.toString() : null;
	}

}
