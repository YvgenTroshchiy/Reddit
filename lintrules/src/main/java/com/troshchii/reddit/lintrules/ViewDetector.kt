package com.troshchii.reddit.lintrules

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

private const val FIND_VIEW_BY_ID_METHOD = "findViewById"
private const val FIND_VIEW_BY_ID_USAGE_MESSAGE = "findViewById should not be used"

@Suppress("UnstableApiUsage")
class ViewDetector : Detector(), SourceCodeScanner {

    override fun getApplicableMethodNames(): List<String> = listOf(FIND_VIEW_BY_ID_METHOD)

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        println("CH_D: ${method.name}")

        context.report(
            ISSUE_FIND_VIEW_BY_ID_USAGE,
            node,
            context.getLocation(node),
            FIND_VIEW_BY_ID_USAGE_MESSAGE
        )
    }

    companion object {
        val ISSUE_FIND_VIEW_BY_ID_USAGE = Issue.create(
            id = "FindViewByIdUsage",
            briefDescription = FIND_VIEW_BY_ID_USAGE_MESSAGE,
            explanation = """Accessing a view should be done through view binding opposed to `findViewById`""".trimIndent(),
            category = Category.CORRECTNESS,
            severity = Severity.ERROR,
            priority = 7,
            implementation = Implementation(ViewDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }
}
