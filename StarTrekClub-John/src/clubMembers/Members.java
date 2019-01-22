package clubMembers;

import java.io.Serializable;

class Members implements Serializable{


	private static final long serialVersionUID = 1L;
	private String firstName, surname, street, town, county, subscription, position;
	int age;

	public Members(String firstNameIn, String surnameIn, String streetIn, String townIn, String countyIn, 
			String subscriptionIn, String positionIn, int ageIn)
	{
		this.firstName = firstNameIn;
		this.surname = surnameIn;
		this.street = streetIn;
		this.town = townIn;
		this.county = countyIn;
		this.subscription = subscriptionIn;
		this.position = positionIn;
		this.age = ageIn;
	}

	public void setFirstName(String firstNameIn)
	{
		firstName = firstNameIn;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setSurname(String surnameIn)
	{
		surname = surnameIn;
	}
	
	public String getSurname()
	{
		
		return surname;
	}
	
	public void setStreet(String streetIn)
	{
		street = streetIn;
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public void setTown(String townIn)
	{
		town = townIn;
	}
	
	public String getTown()
	{
		return town;
	}
	
	public void setCounty(String countyIn)
	{
		county = countyIn;
	}
	
	public String getCounty()
	{
		return county;
	}
	
	
	public String getAddress()
	{
		String address  ="\t" +street+ ",\n\t" +town+ ",\n\t" +county+ ", ";
		return address;
	}

	public void setSubscription(String subscriptionIn)
	{
		subscription = subscriptionIn;
	}

	public String getSubscription()
	{
		return subscription;
	}

	public void setPosition(String positionIn)
	{
		position = positionIn;
	}

	public String getPosition()
	{
		return position;
	}

	public void setAge(int ageIn)
	{
		age = ageIn;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public String toString() 
	{
		String person = "\n name:\t" +firstName+ " " +surname+ ",\n address: " +getAddress()+ "\n Subscription :\t" +subscription+ 
				"\n Role in Club :\t" +position+ "\n Age:\t" +age+ "\n";
		
		return person;
	}

}