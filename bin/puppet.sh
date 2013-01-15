#!/bin/bash
# -u username
# -p password
# -s server list
# -c command or script

#Help Document Start
if [[ "$1" -eq "--help" ]]; then
	echo "======================================================================="
	echo ""
	echo "puppet -u username -p password -s server_list -c script_text"
	echo "puppet -u puppet -p puppet -s server/server.list -c script/test.sh"
	echo ""
	echo "======================================================================="
fi
#Help Document End


#Process Check Start
java_pid=`ps -ef | grep "puppet" | tr -s ' ' |cut -d ' ' -f 3`

echo $java_pid

if [[ "$java_pid" -ne 0 ]]; then
	echo "Puppet  is already running with - PID:[$java_pid]..."
  sleep 1
  exit 0
fi
#Process Check End

#Argument Validation Start
if [[ $# -gt 8 ]]; then
	echo "Argument Size Too Long"
fi

for arg in $@
do
	case "$arg" in

  -u )    username_flag=1
          ;;

  -p )    password_flag=1
          ;;

  -s )    serverlist_flag=1
          ;;

  -c )    script_flag=1
          ;;

  * )     ;;
  esac
done

if [[ "$username_flag" -ne 1 ]]; then
  echo "Username Donot Specified"
  echo "run 'puppet --help' to see help document"
  exit 0
fi
if [[ "$password_flag" -ne 1 ]]; then
  echo "Password Donot Specified"
  echo "run 'puppet --help' to see help document"
  exit 0
fi
if [[ "$serverlist_flag" -ne 1 ]]; then
  echo "Server List Donot Specified"
  echo "run 'puppet --help' to see help document"
  exit 0
fi
if [[ "$script_flag" -ne 1 ]]; then
  echo "Script Text Donot Specified"
  echo "run 'puppet --help' to see help document"
  exit 0
fi
#Argument Validation End


if [[ -f $PUPPET_HOME/target/puppet-aplha_0.1.jar ]]
then
	echo "locating jar file"
	#java -jar $PUPPET_HOME/target/puppet-aplha_0.1.jar $@
	java -jar target/puppet-aplha_0.1-jar-with-dependencies.jar $@
else
	echo "Jar File Not Found"
fi
