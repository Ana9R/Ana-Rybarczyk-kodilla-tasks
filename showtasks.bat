call runcrud.bat
if "%ERRORLEVEL%" == "0" goto rename
echo.
echo runcrud.bat has errors - breaking work
goto fail

:runmozilla
cd "C:\Program Files (x86)\Mozilla Firefox"
start firefox.exe http://localhost:8080/crud/v1/task/getTasks

goto end

:fail
echo.
echo There were errors

:end
echo Work is finished.