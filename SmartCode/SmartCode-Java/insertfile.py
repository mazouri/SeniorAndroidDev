#!/usr/bin/env python
# encoding:utf-8
import sys

"""
    四个参数：1.文件名 2.开始行 3.结束行 4. 插入的内容 （5.开始位置还是结束位置（默认在开始位置））
    exp: ./insertfile.py test.txt 6 9 '- '
"""
filename = sys.argv[1]
#print(filename)
file = open(filename, 'r')
lineList = file.readlines()
for lineNumber in range(int(sys.argv[2])-1, int(sys.argv[3])):
    lineList[lineNumber] = sys.argv[4]+lineList[lineNumber]
file.close()

file = open(filename, 'w')
file.writelines(lineList)
file.close()

print("you have insert '" + sys.argv[4] + "' before lines from " + str(int(sys.argv[2])-1) + " to " + str(int(sys.argv[3])))