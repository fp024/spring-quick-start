@ECHO OFF
SETLOCAL
ECHO [Generate VO, Mapper...]
CALL mvn mybatis-generator:generate

echo.
echo Build log checked? Press Enter to close...
set /p dummyVar=""