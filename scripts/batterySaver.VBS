Set WshShell = CreateObject("WScript.Shell") 
WshShell.Run chr(34) & "<scriptDir>\startup.cmd" & Chr(34), 0
Set WshShell = Nothing
