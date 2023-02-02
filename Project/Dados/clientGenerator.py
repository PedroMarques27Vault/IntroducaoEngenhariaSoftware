import random

# CLIENT ----
gender_options = ["F", "M", "Other"]
pref_options = ["W", "M", "B"]
interests_options = ["Acrobatics", "Blogging", "Reading", "Knitting", "Listening to music", "Listening to podcasts", "Bowling", "Car fixing", "Chess", "Photography", "Running", "Dancing", "Fishing", "Hiking", "Watching movies", "Watching series", "Journaling", "Parkour", "Baseball", "Paintball", "Yoga", "Cycling"]
state_options = ["Accepted", "Reported", "Pending"]
bio = "This is the bio"
user_desc = "This is the description"
user_type = "Client"

alpahnumeric = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789'

female_names = []
male_names = []
last_names = []

femaleNameFile = open("female-first-names.txt", "r")
maleNameFile = open("male-first-names.txt", "r")
lastNameFile = open("last-names.txt", "r")

for line in femaleNameFile:
	female_names.append(line.lower().strip())

for line in maleNameFile:
	male_names.append(line.lower().strip())

for line in lastNameFile:
	last_names.append(line.lower().strip())

textUser = "INSERT INTO User (email,password,userType,startDate) VALUES "
textClient = "INSERT INTO Client (email,username,name,gender,sexual_orientation, bio, description, interests, state, age, birthday) VALUES "

client_email_list = []

for i in range(0,1000):
	x = random.randint(0,len(gender_options)-1)

	clientGender = gender_options[x]
	womORmanName = 2
	if clientGender == "Other":
		womORmanName = random.randint(0,1)
	
	if clientGender == "F" or womORmanName==0:
		rand = random.randint(0,len(female_names)-1)
		first_name = female_names[rand]
		female_names.pop(rand)

	if clientGender == "M" or womORmanName==1:
		rand = random.randint(0,len(male_names)-1)
		first_name = male_names[rand]
		male_names.pop(rand)
	
	rand = random.randint(0,len(last_names)-1)
	last_name = last_names[rand].capitalize()
	
	clientUsername = first_name + last_name
	clientName = first_name.capitalize() + " " + last_name
	clientAge = random.randint(18,40)
	
	startDate = str(random.randint(2017,2020)) + "-" +str(random.randint(1,12)) +"-"+str(random.randint(1,28))

	clientEmail = clientUsername + "@gmail.com"
	clientPassword = ''.join(random.sample(alpahnumeric,8))
	
	clientProfilePicName = clientUsername + "profilePic"
	clientInterests = ', '.join(random.sample(interests_options,5))
	clientPreferences = pref_options[random.randint(0,len(pref_options)-1)]
	clientState = state_options[random.randint(0,len(state_options)-1)]
	
	textUser += "('"+clientEmail+"', '"+clientPassword+"', '"+user_type+"', '"+startDate+"'), "
	birthday = str(random.randint(1950,2004)) + "-" +str(random.randint(1,12)) +"-"+str(random.randint(1,28))
	if i==999:	
		textClient += "('"+clientEmail+"', '"+clientName+"', '"+clientUsername+"', '"+clientGender+"', '"+clientPreferences+"', '"+bio+"', '"+user_desc+"', '"+clientInterests+"', '"+clientState+"', '"+clientProfilePicName+"', "+str(clientAge)+", '"+ birthday +"');"
	else:
		textClient += "('"+clientEmail+"', '"+clientName+"', '"+clientUsername+"', '"+clientGender+"', '"+clientPreferences+"', '"+bio+"', '"+user_desc+"', '"+clientInterests+"', '"+clientState+"', '"+clientProfilePicName+"', "+str(clientAge)+", '"+ birthday +"'),\n"
	
	client_email_list.append(clientEmail)


# MATCHES
textMatches = "INSERT INTO Match (email1,email2) VALUES "
matches_list=[]

for i in range(0,500):
	match1 = random.randint(0, len(client_email_list)-1)
	match2 = random.randint(0, len(client_email_list)-1)
	
	client1 = client_email_list[match1]
	client2 = client_email_list[match2]

	if match1!=match2 and ( (client1,client2) not in matches_list or (client2,client1) not in matches_list ):
		matches_list.append((client1, client2))
		if i == 499:
			textMatches += "('"+client1+"', '"+client2+"');"
		else:
			textMatches += "('"+client1+"', '"+client2+"'),"


# NEGOCIO PROMOÇÕES SUBSCRIÇÕES
business_email_list = []

textBusiness = "INSERT INTO Business (email,name,address,description,businessType,businessState) VALUES "
textPromotion = "INSERT INTO Promotion (email,title,description,promotionCode,imageId) VALUES "
textSubscription= "INSERT INTO Subscription (email,startDate,endDate,monthlyFee) VALUES "

restaurants_list = []
florist_list = []

restaurantsFile = open("restaurant_names.txt", "r")
floristsFile = open("florist_names.txt", "r")

for line in restaurantsFile:
	restaurants_list.append(line.lower().title().strip().replace("'",""))

for line in floristsFile:
	florist_list.append(line.strip())

businessState_options = ["Accepted", "Reported", "Pending"]
businessDesc = "This is the description of this business"

subscriptionFees = [100,250,500]
businessList = list(set(restaurants_list+florist_list))
length=len(businessList)
used = []
for i in range(0,length):
	rand = random.randint(0,length-1)
	businessName = businessList[rand]
	
	while businessName in used:
		rand = random.randint(0,length-1)
		businessName = businessList[rand]

	used.append(businessName)
	businessType="Restaurant"
	if businessName not in restaurants_list:
		business = "Florist"
		
	businessAddress = businessName.lower().replace(" ","")+".com"
	businessStartDate = str(random.randint(2017,2019)) + "-" +str(random.randint(1,12)) +"-"+str(random.randint(1,28))

	businessEmail = businessAddress + "@gmail.com"
	businessPassword = ''.join(random.sample(alpahnumeric,8))
	
	businessState = businessState_options[random.randint(0,len(businessState_options)-1)]

	business_email_list.append(businessEmail)

	#PROMOTION
	promotionTitle = "Promotion of" + businessName
	promotionDesc = "Description of the Promotion"
	promotionCode = ''.join(random.sample(alpahnumeric,8))
	promotionImageID = i


	#SUBSCRIPTION
	subscriptionEndDate = "Null"
	if i%10==0:
		subscriptionEndDate = "2020" + businessStartDate[4:]
	
	subscriptionMonthlyFee = subscriptionFees[random.randint(0,len(subscriptionFees)-1)]
	

	if i==length-1:	
		textUser +=   "('"+businessEmail+"', '"+businessPassword+"', '"+businessType+"', '"+businessStartDate+"');"
		textBusiness += "('"+businessEmail+"', '"+businessName+"', '"+businessAddress+"', '"+businessDesc+"', '"+businessType+"', '"+businessState+"');"
		textSubscription += "('"+businessEmail+"', '"+businessStartDate+"', '"+subscriptionEndDate+"', "+str(subscriptionMonthlyFee)+");"
		textPromotion += "('"+businessEmail+"', '"+promotionTitle+"', '"+promotionDesc+"', '"+promotionCode+"', "+str(promotionImageID)+");"
	else:
		textUser +=   "('"+businessEmail+"', '"+businessPassword+"', '"+businessType+"', '"+businessStartDate+"'),"
		textBusiness += "('"+businessEmail+"', '"+businessName+"', '"+businessAddress+"', '"+businessDesc+"', '"+businessType+"', '"+businessState+"'),"
		textSubscription += "('"+businessEmail+"', '"+businessStartDate+"', '"+subscriptionEndDate+"', "+str(subscriptionMonthlyFee)+"),"
		textPromotion += "('"+businessEmail+"', '"+promotionTitle+"', '"+promotionDesc+"', '"+promotionCode+"', "+str(promotionImageID)+"),"




writeFile = open("../users.sql", "w")
writeFile.write(textUser)

writeFile = open("../clients.sql", "w")
writeFile.write(textClient)

writeFile = open("../matches.sql", "w")
writeFile.write(textMatches)

writeFile = open("../business.sql", "w")
writeFile.write(textBusiness)

writeFile = open("../promotions.sql", "w")
writeFile.write(textPromotion)

writeFile = open("../subscriptions.sql", "w")
writeFile.write(textSubscription)