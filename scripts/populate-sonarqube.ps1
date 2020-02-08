param(
    [Parameter(Mandatory=$true)][string]$ParentFolder,
    [Parameter(Mandatory=$true)][string]$OutputFolder
)
Clear-Host

$ScriptBlock = { param($ParentFolder,$OutputFolder)
    $Guid = [Guid]::newGuid()
    Write-Output "Writing SonarQube project-$Guid from generated folder $OutputFolder/$Guid"
    Copy-Item -Path "$ParentFolder" -Destination "$OutputFolder/$Guid" -Recurse
    & mvn package -q -DskipTests -D"version=$Guid-SNAPSHOT" -f "$OutputFolder/$Guid/pom.xml" sonar:sonar -D"sonar.host.url=http://192.168.2.155:19100" -D"sonar.projectKey=project-$Guid"
}

for($i = 0; $i -lt 20; $i++)
{
    Start-Job $ScriptBlock -ArgumentList $ParentFolder,$OutputFolder
}

Measure-Command {
    While (Get-Job -State "Running") {Start-Sleep 2}
}

Get-Job | Receive-Job

Remove-Job *