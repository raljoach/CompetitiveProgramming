import sys
import math

bestMinPiece=-1
def main():
	#2D-array named grid
	grid = [[9, 7, 6, 8],[6,7,6,7],[5,3,1,3]]
	
	numVertCuts = 2
	numHorzCuts = 2
	
	solve(grid,numVertCuts,numHorzCuts)
	print "Ans: " + str(bestMinPiece)
	
def solve(grid,numVertCuts,numHorzCuts):
	vCutsList = getCuts(len(grid),numVertCuts)
	hCutsList = getCuts(len(grid[0]),numHorzCuts)
	
	for vCuts in vCutsList:
		for hCuts in hCutsList:
			calcBestMinPiece(grid,vCuts,hCuts)
	
	return bestMinPiece

def getCuts(length,numCuts):
	set = []
	for j in range(1,length):
		set.append(j)
	
	results = []
	n = int(math.pow(2,length-1))
	for i in range(0,n):
		k=i
		index=0
		combo = []
		while (k):
			combo.append(set[index])
			k = k>>1
			index = index+1
		
		if len(combo) == numCuts:
			results.append(combo)
	
	return results

def calcBestMinPiece(grid,hCuts1,vCuts1):
	print "Vertical cuts:"
	for v in vCuts1:
		print str(v) + " "
	
	print ""
	
	print "Horizontal cuts:"
	for h in hCuts1:
		print str(h) + " "
	
	print ""
	print ""
	
	vCuts = []
	vCuts.extend(vCuts1);
	vCuts.append(len(grid[0]))
	hCuts = []
	hCuts.extend(hCuts1);
	hCuts.append(len(grid))
	
	vPrev=0
	min1 = sys.maxint
	for i in range(len(vCuts)):
		v = vCuts[i]
		hPrev = 0
		for j in range(len(hCuts)):
			h = hCuts[j]
			thisSum = getSum(grid,hPrev,h,vPrev,v)
			print "This sum:" + str(thisSum)
			min1 = min(min1,thisSum)			
			hPrev = h
		print "---------------------------"
		vPrev = v
	global bestMinPiece    
	bestMinPiece = max(min1, bestMinPiece)
	print "Calculated min: " + str(min1)
	print "Best min piece:" + str(bestMinPiece)
	print "----------------------------"

def getSum(grid,rowStart,rowEnd,colStart,colEnd):
	print "Colstart:" + str(colStart) + " Colend:" + str(colEnd)
	print "Rowstart:" + str(rowStart) + " Rowend:" + str(rowEnd)
	
	sum = 0;
	for i in range(rowStart,rowEnd):
		for j in range(colStart,colEnd):
			sum += grid[i][j]
	
	return sum
