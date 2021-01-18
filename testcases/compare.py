choose=input("Original [press 0] or New [press 1]?")
if(choose=="1"):
	filename1 = input("input file: ") 
	filename2 = input("Output file: ") 
else:
	filename1 = "file1.txt"
	filename2 = "file2.txt"

file1 = open(filename1).readlines() 
 
file1_line = [] 
 
for lines in file1: 
	file1_line.append(lines) 
 
file2 = open(filename2).readlines() 
 
file2_line = [] 
 
for lines in file2: 
	file2_line.append(lines) 
import difflib
print("Length of case file 2: "+str(len(file2_line)))
print("Length of case file 1: "+str(len(file1_line)))
count=0
diff = 0
line_diff=0
for i in range(min(len(file2_line),len(file1_line))):
	temp=diff
	case_a = file2_line[i]
	case_b = file1_line[i]

	#output_list = [li for li in difflib.ndiff(case_a, case_b) if li[0] != ' ']
	if(len(case_a)==len(case_b)):
		print(str(i+1)+" line length is same ")
	else:
		line_diff+=1
		print("Length of case b: "+str(len(case_b)))
		print("Length of case a: "+str(len(case_a)))
	# print(case_b[-2])
	# print(case_a[-1])
	for j in range(min(len(case_b),len(case_a))):
		count+=1
		if(case_b[j]!=case_a[j]):
			print(diff)
			diff+=1
			for jj in range(10):
				print(case_b[j+jj],end="")
			print()
			for jj in range(10):
				print(case_a[j+jj],end="")
			print()
	if(temp==diff):
		print(str(i+1)+" line matched")
	else:
		print("###########################################################################")
		print("Note this")
print("Total characters checked: "+str(count))
print("Total difference found: "+str(diff))
print("Total Line length difference found: "+str(line_diff))

if(diff==0):
	print("Both the outputs Matched. Eureka!!")
else:
	print("Outputs not matched. Try again later!!")	