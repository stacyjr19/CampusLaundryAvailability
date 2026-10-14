Campus Laundry Availability Application
Course: COM-437 Android Programming | Student: Stacy Linder Jr.
GitHub Classroom repository: [INSERT REPOSITORY URL]
Project Description
An Android application that displays campus washer and dryer status and provides a Refresh control to request an updated display.
Problem Addressed
Students benefit from checking laundry availability before walking to the laundry room. Live status accuracy depends on the actual data source and is not asserted here.
Platform and Architecture
- Android Studio / Android application
- Front end: machine availability display, status labels, Refresh button
- Back end: existing project status logic/data source (implementation details to be confirmed from code)
- Future option: API-backed, shared status database
Functionality
1. Open the app.
2. Review washer/dryer status.
3. Select Refresh Status.
4. Review the updated display.
Wireframe (conceptual)
+--------------------------------+
|         CAMPUS LAUNDRY         |
|      Machine Availability      |
| Washer 1       Available       |
| Washer 2       In Use          |
| Dryer 1        Available       |
|       [ Refresh Status ]       |
+--------------------------------+
Run and Test
1. Clone the GitHub Classroom repository and open it in Android Studio.
2. Allow Gradle sync to finish; configure an emulator or device.
3. Run the application and check the status display and Refresh behavior.
4. Record the results and any errors. The developer has reported that the current application works; independent evidence is not attached here.
Version Changelog
Version	Stage	Update
v1	Previous; student-reported complete	Initial laundry status interface
v2	Current; student-reported complete	Refresh button and functioning display
Documentation revision	Current	Formal outline, README, Wiki draft, investor presentation
Final delivery	Planned	Regression testing, GitHub publication, APK/ZIP/QR packaging


Rubric Verification Checklist
- [ ] Button, image, canvas, arrangements, sound/invisible components, and sensors verified against source
- [ ] Behaviors, event handlers with arguments, and variables verified against source
- [ ] APK built and tested
- [ ] Source ZIP created
- [ ] QR code linked to a publicly accessible APK URL
- [ ] Wiki published and README direct URL submitted
The component checklist reflects rubric requirements, not confirmed implementation. The rubric may refer to a visual block editor rather than Android Studio.

Reflection
The project applies iterative development to a practical campus problem. The refresh enhancement illustrates the importance of clear user actions, repeatable tests, and maintaining project documentation for future development.
Wiki
[INSERT PUBLISHED GITHUB WIKI URL]
References
- Android Developers. (n.d.). Guide to app architecture. https://developer.android.com/topic/architecture
- Android Developers. (n.d.). Build your first Android app. https://developer.android.com/get-started/overview
- GitHub Docs. (n.d.). About READMEs. https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/about-readmes
- GitHub Docs. (n.d.). About wikis. https://docs.github.com/en/communities/documenting-your-project-with-wikis/about-wikis
