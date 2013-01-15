#!/bin/bash
# -----------------------------------------------------------------------------
# run puppet
# 
# @author Chan Chen
# -----------------------------------------------------------------------------

java_pid=`ps -ef | grep "puppet" | tr -s ' ' |cut -d ' ' -f 1` 


if [[ $java_pid -ne 0 ]]; then 
echo "Puppet  is already running with - PID:[$java_pid]..."
sleep 1
exit 0
fi

echo
echo Error while starting Puppet on 'uname -n'
echo

sleep 1
