package com.troshchii.reddit.lintrules

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue

class MyIssueRegistry : IssueRegistry() {

    override val issues: List<Issue> = listOf(
        ViewDetector.ISSUE_FIND_VIEW_BY_ID_USAGE
    )
}
