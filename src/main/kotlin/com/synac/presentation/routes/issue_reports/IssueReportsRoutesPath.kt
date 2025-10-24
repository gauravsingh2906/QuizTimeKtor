import io.ktor.resources.*

@Resource(path = "/report/issues")
class IssueReportsRoutesPath {

    @Resource(path="{reportId}")
    data class ById(
        val parent:IssueReportsRoutesPath = IssueReportsRoutesPath(),
        val reportId:String
    )

}