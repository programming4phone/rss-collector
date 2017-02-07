# RSS Collector Web Service

The primary purpose of this web service is to access RSS feeds and return the results as JSON with CORS response headers, specifically for use by an Angular 2 application.

Currently the only supported RSS feed originates from the [nwitimes.com] (http://www.nwitimes.com/pages/rss/) news service.

## Using this web service

This web service is currently deployed on Heroku and can be accessed in a web browser using this link `https://rss-collector-001.herokuapp.com/rssAsJson/{feedId}` and substituting the `{feedId}` with one of the following values: 

`nwitimes-latest`, `nwitimes-lake`, `nwitimes-porter`, `nwitimes-illinois`, `nwitimes-indiana`, `nwitimes-national`, or `nwitimes-world`.

For example, to get the headlines for Lake County, IN, use this link: 

[https://rss-collector-001.herokuapp.com/rssAsJson/nwitimes-lake] (https://rss-collector-001.herokuapp.com/rssAsJson/nwitimes-lake).

## Development stack

This project was developed using Java 8, Spring Boot, and Rome Tools.

## Build

Run `mvn clean install` to build the project and run the supplied unit tests. The build artifacts will be stored in the `target/` directory. 


