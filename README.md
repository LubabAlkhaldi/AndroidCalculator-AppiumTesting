# 🧮🤖Calculator Testing Mobile

This is a mobile automation testing project for the default Android Calculator app using **Appium**, **TestNG** and **Java**.

## ✅ Tools Used
- Java
- Appium Server
- Appium Inspector
- Android Emulator 
- TestNG

## 📱 What this project does:
1. Opens the Calculator app on an Android emulator.
2. Clicks on two random numbers and performs addition.
3. Validates that the result is correct.
4. Includes additional tests for:
   - Clicking all number buttons.
   - Clicking odd number buttons.
   - Performing multiplication.

## 🛠 How to Run
- Make sure Appium Server is running.
- Connect your Android emulator or real device.
- Run tests from TestNG or your IDE.

## 📂 Structure
- `myTestCases.java`: Contains all test methods.
- `@BeforeTest`: Sets up Appium desired capabilities.
- `@BeforeMethod`: Starts a new Appium session before each test.
- `@Test`: Contains test logic.
- `@AfterTest`: Placeholder for teardown if needed.
