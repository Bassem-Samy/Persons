# Persons
## App Description:
The app starts with a login screen, where the user get to enter the email and password for his/her account. </br>
Then the user is prompeted to see a list of the contacts and when clicks on any, a details screen is showed where the user info is displayed.
## Technical Description:
* The app is structured in MVP.
* My most valuable reference for implementing MVP in android is [Antonio Leiva's Post](https://www.dropbox.com/s/r0wy953e50q0cra/feedback-app-release.apk?dl=0)
* To store the list of person (contacts) a SQLite database is used, made by the native approach by extending SQLiteOpenHelper class.
* Dagger 2 is used for depenedency injection, to inject dependencies in the person listing and person details modules.
* I have added an android unit test using espresso to test the login validations and that the listing of person is displayed correctly.
* I have added a unit test for the PersonDataConverter class where this class is responsible to convert the person object that comes from the api to a person object suitable for saving in the db
* 3rd party libraries used:RxJava/RxAndroid 2, Dagger2, Butterknife, Retrofit2 and Glide.
