# VegStore
Application for a vegetable store with a static known number of Items to make a selling process with recording and give a receipt for the client.

## Project description

### App features:
The application can help you to track your cachers sales, for your store, And this is the big picture of the app services:
- Make Order Service: This helps the Cacher to make an order for a client.
- Last 30 days analytics: This service Helps you to show all sales for each cacher for the last 30 days.

### App technologies:
For this app, I used these technologies:
- Java programming language: for training reasons.
- SWING: I used this library for building the UI of the application.
- SQLite DB: The app is simple and I find that it will be enough.
- Class diagrams with UML: This is one of the main reasons for building the app. I learned about this diagram and its principles and built this project to test my knowledge.
- CRC: This is also the type of diagram that I used to present the conceptual design for the app.
- Sequence diagram: I used it before building, to have a picture of how code will work for buying items or request analytics.
- JDBC driver: I used this driver to reference my app to help my Java code communicate with SQLite.

### Main challenges I face in this project:
There are many challenges that I faced from the beginning of the planning for the application to the end of this process, and these are the main challenges:
- At the beginning of the planning I found it's a challenging problem to break my project into components, that could apply the MVC pattern.
- In the middle of the project I found a problem dealing with outdated technology like ***SWING***, and how to build the UI of the application.
- At the end of the project I found a problem with the DB: I found some confusion about closing and connecting the DB. It makes a huge and strange bugs, That I should deal with them.

## Installing and Running
### Install the project:
To install the project you can open your terminal and enter this command:
```
git clone https://github.com/Ahmed101Mohammed/VegStore.git
```
### Run the project:
If you expert than me I expect that you can run this project in way easy than I am. What I will explain is takes some time because I can use the project only in the VS code terminal.
follow these steps:
#### First Step:
If you don't have extensions to run Java code install these extensions from vs code extensions:
- Extension Pack for Java (This extension contains all the extensions you need).
#### Second Step:
Install the jar file of [this website](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc/3.42.0.1)
#### Third Step:
Open the project folder with vs code by typing:
```
code .
```
In the terminal when being in the directory of the project "VegStore".

#### Fourth Step:
- In the VS code environment in the EXPLORER tap, you will find at the bottom this tap: "> JAVA PROJECTS".
- Click on the tap and you will see a new tap called: "> [] VegStore".
- Click on it and many taps will appear, Make the mouse over on this tap: "> [] Referenced Libraries"
- Then the plus + sign will appear. Press it and find the file that you installed in the ***Therd Step***.

#### Fifth Step (finally):
In the VS Code environment go to the **app** folder and open the **Main.java** file.
Above the **main** method, you will find the ***Run*** word, Press it.
The project will run and the signing window will be opened.

## How to use the APP:
You can easily use the application, The application easy to use because there is no special or huge number of functionality.

## MIT License:
MIT License

Copyright (c) 2023 Ahmed Mohamed 3adl

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

## How you can Contribute
I expect that you know how to fork and pull in GitHub.
All you can do is: pull your contribution with none strange files. And a deep explanation about the bug that you solved or the feature that you added.
I hope you find a better way to run the app and upgrade the ReadMe file with it. But this way should work with me to accept it.
Thanks.
