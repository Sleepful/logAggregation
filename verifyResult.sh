#!/usr/bin/env bash

sort -c final.log
if [ $? -eq 0 ]; then
    echo OK
else
    echo FAIL
fi
