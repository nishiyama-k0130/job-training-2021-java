# Sample TODO Application
Sample Java Spring Boot Application

# Prerequisites
- java 17
- maven 3.6.3
- VS Code

# You can access index page
http://localhost:8080/

# Construction on VSCode

## Install VS Code
https://code.visualstudio.com/docs/setup/mac

## Download jdk Amazon Correto 17
JDK version [Amazon Correto 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)

Unpack and put it under ~/develop/

## Install VS Code Java Extension Pack
https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack

## Set java.home in VS Code
Code > Preference > Settings > Search java.home > edit settings.json

Specify java.home.

Mac Ex)

"java.home": "/Users/N164/develop/amazon-corretto-17.jdk/Contents/Home"

## Install maven
Download<br>
https://ftp.riken.jp/net/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip

Unpack and put it under ~/develop/

## Set maven path in VS Code
Code > Preference > Settings > Search maven.executable.path

Mac Ex)

/Users/N164/develop/apache-maven-3.6.3/bin/mvn

# tools
## Formatter
We use [google java style formatter](https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml)<br>
[Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
```bash
mvn formatter:format
```

## Checkstyle
We use [google checkstyle](https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/google_checks.xml).<br>
But customized bit.
```bash
mvn checkstyle:check
```

