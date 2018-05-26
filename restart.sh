#!/bin/bash

ps -ef | grep zippit-backend | awk '{print $2}' | grep -v 'grep' | xargs kill

sh /home/ubuntu/zippit/start.sh

