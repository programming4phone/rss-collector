package com.programming4phone.rss.collector;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.programming4phone.rss.collector.admin.RssFeedException;
import com.programming4phone.rss.collector.entity.Article;

@RestController
public class RssController {
	
	@Autowired
	private Environment environment;

	@Autowired
	private ArticleExtractor articleExtractor;
	
	@RequestMapping(value="/rssAsJson/{feedId}", 
			method = RequestMethod.GET,
			produces={"application/json"})
	public List<Article> getFeedDisplayInfo(@PathVariable("feedId") String feedId) {
		String rssUrl = Optional.ofNullable((String)environment.getProperty(feedId)).orElseThrow(()->new RssFeedException("Unsupported Feed: "+feedId));
		return articleExtractor.apply(rssUrl);
	}
}
