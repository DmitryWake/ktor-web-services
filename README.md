# ktor-web-services
## First way to start (with IDE)
1. Open IntelliJIdea
2. Clone this repository
3. Sync with gradle files
4. Launch main function

## Second way to start (without IDE, only JAVA)
1. You have to get JDK installed and JAVA_HOME configured
2. Clone git repo
3. Open ktor-web-services/fatjar
4. Use terminal: `java -jar .\com.example.ktor-web-services-all.jar`

## Availible methods
1. `get(/)` - just repond "Hello world"
2. `post(/words)` - request body example: `{
    "letter":"c",
    "words":[
        "word",
        "name",
        "char",
        "key",
        "chair"
    ]
}`. response body example: `{
    "letter": "c",
    "words": [
        "char",
        "chair"
    ]
}`
3. `post(/grayscale/upload)` - expected image as input. response image.png with grayscale as output
