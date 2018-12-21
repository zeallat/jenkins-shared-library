#!/usr/bin/env groovy

/**
 * Send notifications based on build status string
 */
def call(String buildStatus = 'STARTED') {
  // build status of null means successful
  buildStatus = buildStatus ?: 'SUCCESS'

  // Default values
//  def colorName = 'RED'
//  def colorCode = '#FF0000'
  def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
  def summary = "${subject} (${env.BUILD_URL})"
  def details = "Build for ${env.JOB_NAME} [${env.BUILD_NUMBER}] is ${buildStatus}! Check console output at (${env.BUILD_URL})"

  // Override default values based on build status
  if (buildStatus == 'STARTED') {
    color = 'YELLOW'
    colorCode = '#FFEB3B'
  } else if (buildStatus == 'SUCCESS') {
    color = 'GREEN'
    colorCode = '#00E676'
  } else {
    color = 'RED'
    colorCode = '#FF3D00'
  }

  // Send notifications
  slackSend (color: colorCode, message: details)
}
