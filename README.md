# Big-data project
 - This project is an ensemble of multiple big-data applications- all dockerized to work as a group providing weatherdata analytics.
 - The source of this data is: [OpenWeatherMapAPI](https://www.openweathermap.org/)
## Architecture
<img src="https://github.com/suchiksagar/bigdataproject/blob/master/documentation/architecture.jpg" alt="Image Goes Here"/>

## Description
### dataservices (AWS EC2 cluster)
It includes 2 sub projects 

    - weatherdataws:
         - This is a spring boot REST project that intercats with OpenWeatherMap API periodically (every 12 secs) to fetch the data based on an iterative index of a list of cities.
         - The time interval of 12 secs has been carefully chosen to spread the threshold of 7200 calls per day(24 hrs) over 300 cities across US every 4 hours
         - There is a admin weatherdataws/v1/admin endpoint that accepts start/stop path params to kick off the job/stop the job
    - filepollws:
        - This spring boot REST web service works in tandem with the previous one only that this retrieves the most recent weather record (every call fecthes a record) at any given moment

### 