		
		//data si ora curenta
		
		DateFormat data = new SimpleDateFormat("dd.MM.YYYY hh:mm:ss");
        Date dataSiOra = new Date();
        String dateHour=data.format(dataSiOra);


		//SharedPreferences
        SharedPreferences sp = getSharedPreferences("dataInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("data",dateHour);
        editor.commit();
		
		
		//Datepicker
		 DatePicker datePicker=findViewById(R.id.datepicker);
        Calendar c =Calendar.getInstance();
        c.set(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth());
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd.MM.YYYY");
        String data = dateFormat.format(c.getTime());
		
		
		//To TXT
		FileOutputStream fos=null;
		try{
			fos=openFileOutput("yeet.txt",MODE_PRIVATE);
			fos.write(myString.getBytes());
			
		}finally{
		fos.close;
		}
		
		
		
	
		