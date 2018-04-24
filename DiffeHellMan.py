import math
import random

p = input("Enter p value ")
g = input("Enter g value ")
print "p and g values are: ",p," ",g

sa = random.randint(1,10)
sb = random.randint(1,10)
print "SA and SB values are: ",sa," ",sb

ta = int((math.pow(g,sa))%p)
tb = int((math.pow(g,sb))%p)
print "TA and TB values are ",ta," ",tb

secretKey1 = int((math.pow(tb,sa))%p)
secretKey2 = int((math.pow(ta,sb))%p)

if(secretKey1 == secretKey2):
    print "Bob and Alice has the same secret key!!"
    print "Secret key is ",secretKey1
else:
    print "Secret Key Mismatched"
