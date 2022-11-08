def call () {
 publishHTML (target : [allowMissing: true,
    alwaysLinkToLastBuild: true,
    keepAll: true,
    reportDir: 'target/site/',
    reportFiles: 'index.html',
    reportName: 'Site Report',
    reportTitles: 'Site Report'])
}
