Thank you for using CEMS system.

Preconditions for using the system:
1. Copy both folders listed below into your C drive: 
	a. CEMS_server (When server receives files from the client, they are saved under "C://CEMS_server")
	b. CEMS_client (When client receives files from the server, they are saved under "C://CEMS_client")
3. In MYSQL create a new empty scheme - "cems".
4. Import "CEMS_DB".

	(Right click on your connection in MYSQL, in the "Advanced" tab under "Other" add the flag: OPT_LOCAL_INFILE=1
	 Restart MYSQL
	 If that doesn't work, in the MYSQL run the query: SET GLOBAL local_infile = true;)
	 
*** Steps (5) and (6) are to be done only during the first use of the system ***
5. Our server needs to import users, courses and subjects information from an external system 
   (the folder was copied during step (2) in "CEMS_server" and named "External"). 
6. Eventually the user must click the "import" button in the server's window.

7. Enjoy CEMS system.

Further details: 
(*) The password for MySQL should be Aa123456 (or else, changed manually in the class "JDBCSingleton").
(*) Offline tests are to be saved in "CEMS_server" folder 
    (when a user downloads an offline exam, it'll be saved in "CEMS_client" folder).