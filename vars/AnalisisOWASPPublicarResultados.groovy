def call() {
    dependencyCheckPublisher failedNewCritical: 5, failedNewHigh: 15, failedNewLow: 30, failedNewMedium: 15, failedTotalCritical: 10, failedTotalHigh: 30, failedTotalLow: 60, failedTotalMedium: 30, pattern: '**/dependency-check-report.xml', unstableNewCritical: 3, unstableNewHigh: 5, unstableNewLow: 25, unstableNewMedium: 15, unstableTotalCritical: 5, unstableTotalHigh: 20, unstableTotalLow: 50, unstableTotalMedium: 20
}