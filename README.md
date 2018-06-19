# Big-data project
 - This project is an ensemble of multiple big-data applications- all dockerized to work as a group providing weatherdata analytics.
 - The source of this data is: [OpenWeatherMapAPI](https://www.openweathermap.org/)
## Architecture
<img src="https://github.com/suchiksagar/bigdataproject/blob/master/documentation/architecture.jpg" alt="Image Goes Here"/>

## Description
### dataservices (AWS EC2 cluster)
It includes 2 sub projects 
- weatherdataws:
    - This is a spring boot REST project that intercats with OpenWeatherMap API periodically (every 12 secs) to fetch the data based on an iterative index of a list of cities
    - The time interval of 12 secs has been carefully chosen to spread the threshold of 7200 calls per day(24 hrs) over 300 cities across US every 4 hours
    - There is a admin weatherdataws/v1/admin endpoint that accepts start/stop path params to kick off the job/stop the job
- filepollws:
    - This spring boot REST web service works in tandem with the previous one only that this retrieves the most recent weather record (every call fecthes a record) at any given moment

### Flume 
Flume consists of three sub components: 
- curl script:
    - This script constantly polls for the newer data exposed by the data service(filepoll) to fetch a new weather record ebvery 12 seconds and dumps it to a staging area
    - The script also pushes the same file to the s3httpsink microservice (via HTTP POST call) which inturn deposits this file on AWS s3
- Flume Agent:
    - This flume agent reads the files from the staging area which acts as its spool source. It uses a memory channel to transfer the data over to a kafka sink
- Clean Up:
    - Once the data is rolled over from the staging area, the files are automatically purged by the script (bash)

### Kafka
 - The Kafka-Zookeeper ensemble ensures that the weather data is almost always consumed. If the downstream systems are unavailable for some reason say either elastic stack or the kafka consumers go down, the stream still retains these messages for a default 7 day period.
 - They can also be read from the beginning if required so that all the data is carefully injested into elastic.

### s3HttpSink
 - This Spring BOOT REST webservice receives a weather data message via POST call and deposits the message as a file into your s3 account. 
 - The advantage is that you can turn the data injetion on or off at will 
 - This data can further be used to run EMR/spark jobs or may be analyzed using Hive/Pig

### 