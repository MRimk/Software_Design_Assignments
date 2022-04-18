# BEAM 
GPX manager (Strava-like) project created for Vrije Universiteit Amsterdam Computer Science course Software Design by a team of 4 students. Currently being extended by one of them. 
## Further feature ideas
### Multiple users
Users can login to their account to track <b>their</b> own progress.
### Online use of this application
<p>There would be a database of activities which could be opened by each user using this application. This would require redesign of the classes structure and much deeper design of inner workings but general idea is here. </p>
<p>User logs into the application (their personal data is loaded), uploads the activity - we upload gpx file <b>AND</b> analysed metrics. Basically all computing is done on the user device and database would be storing the data, not calculating anything. </p>

### History of activities
User can see and open their prior activities, which would be stored locally and we would keep their relative locations in a JSON file.
