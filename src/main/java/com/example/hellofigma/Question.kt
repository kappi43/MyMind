package com.example.hellofigma

class Question (var questionText: String,
                var answers: MutableList<String> = mutableListOf("Not at all", "Several days", "More than half the days", "Nearly every day"),
                var resp: String = answers[0]
               )
var questions = mutableListOf(
    Question("Little interest or pleasure in doing things?"),
    Question("Feeling down, depressed, or hopeless?"),
    Question("Trouble falling or staying asleep, or sleeping too much?"),
    Question("Feeling tired or having little energy?"),
    Question("Poor appetite or overeating?"),
    Question("Feeling bad about yourself - or that you are a failure or have let yourself or your family down?"),
    Question("Trouble concentrating on things, such as reading the newspaper or watching television?"),
    Question("Moving or speaking so slowly that other people could have noticed?\n" +
            "Or the opposite - being so fidgety or restless that you have been moving around a lot more than usual?"),
    Question("Thoughts that you would be better off dead, or of hurting yourself in some way?")
)