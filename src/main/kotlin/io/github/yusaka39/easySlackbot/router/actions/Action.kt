package io.github.yusaka39.easySlackbot.router.actions

import io.github.yusaka39.easySlackbot.slack.Slack

interface Action {
    companion object {
        fun create(procedure: (Slack) -> Unit) = object : Action {
            override fun run(slack: Slack) = procedure(slack)
        }
    }

    fun run(slack: Slack)
    infix fun compose(doAfter: Action): Action = object : Action {
        override fun run(slack: Slack) {
            this@Action.run(slack)
            doAfter.run(slack)
        }
    }
}