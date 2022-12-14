package com.example.foodieapp;

public class ChapterQuiz1 {
    public static String question[] = {
            "What is the official IDE for Android App development?",
            "True or false? Each app runs in its own process with its own instance of the Android Runtime.",
            "A HAL is a standard interface that exposes device hardware capabilities as libraries. What does HAL stand for?",
            "What does AVD stand for?",
            "ConstraintLayout and LinearLayout are subclasses of?",
            "How many View elements are allowed in a ScrollView?",
            "True or false? Apps you create can be install onto a physical device.",
            "___ is an interface to global information about an application environment.",
            "The correct syntax for crating a Logcat message is?",
            "True or false? A LinearLayout can be nested within a ScrollView.",
            "Which is NOT true about what an Activity does?",
            "An Activity Java class extends ___",
            "True or false? An explicit intent gives the user the option to select which app will handle the request.",
            "The data structure used to manage Activities is a/n?",
            "Temporal and Ancestral navigation are informally referred to as?",
            "When the Activity is in its \"Paused\" state, which of the following are the next possible states?",
            "True or false? onResume() is ALWAYS followed by onPause().",
            "Which of the following methods are called when a user rotates the device?",
            "True or false? Activities can only have one filter?",
            "Which of the following is NOT true when the method onDestroy() is called?"
    };

    public static String choices[][] = {
            {"Android Studio", "Android App Kit", "Android Works", "Android Launcher"},
            {"True", "False"},
            {"Hardware Abstraction Layer", "Hardware Android Link", "Hardware Abstraction Loader", "Hardware and Libraries"},
            {"Android Virtual Device", "Android Visual Device", "Android Virtual Demo", "Application Virtual Demo"},
            {"View", "JPanel", "Layout", "Display"},
            {"1", "0", "5","Infinite"},
            {"True", "False"},
            {"Context", "Source", "Page", "Layout"},
            {"Log.d(TAG, \"Message\")", "Log.d(\"Message\", TAG)", "Log.add(TAG, \"Message\")", "Log.add(\"Message\", TAG)"},
            {"True", "False"},
            {"Automatically updates other Activities with relevant information", "Has a life cycle",
            "Can start activities in other apps", "Handles all user interactions"},
            {"AppCompatActivity", "AndroidCompatActivity", "AndroidAppActivity", "ApplicationActivity"},
            {"False", "True"},
            {"Stack", "Queue", "Linked List", "Array"},
            {"Back and Up navigation", "Left and Right navigation", "Down and Up navigation", "Back and Right navigation"},
            {"Resume and Stopped", "Stopped and Destroyed", "Start and Resume", "Create and Start"},
            {"True", "False"},
            {"onDestroy()", "onRotate()", "onLandscape()", "All of the above"},
            {"False", "True"},
            {"Data and State are temporarily saved for user convenience", "Triggered when the user navigates back to a previous Activity",
                    "Is called when system is trying to save space", "Is always preceded by onStop()"}
    };

    public static String correctAnswers[] = {
            "Android Studio",
            "True",
            "Hardware Abstraction Layer",
            "Android Virtual Device",
            "View",
            "1",
            "True",
            "Context",
            "Log.d(TAG, \"Message\")",
            "True",
            "Automatically updates other Activities with relevant information",
            "AppCompatActivity",
            "False",
            "Stack",
            "Back and Up navigation",
            "Resume and Stopped",
            "True",
            "onDestroy()",
            "False",
            "Data and State are temporarily saved for user convenience"
    };

}
