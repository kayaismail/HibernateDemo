package com.hibernatedemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[] args) {
		SessionFactory factory= new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(City.class)
				.buildSessionFactory();
		//unit of Work design pattern
		Session session =factory.getCurrentSession();
		
		try {  //Cath hibernate in kendinde oldugu icin yazmiyoruz
			session.beginTransaction();
			//HQL HIbernate query languages
			//veri cekme ornekleri Select *from city
			//map ettigimiz city nesnesini cagirdik 'c' diye alias biraktik City yi surekli yazmamak icin
			//List<City> cities = session.createQuery("from City c where c.countryCode='TUR' OR c.countryCode='USA' ").getResultList();
			
			// mysql query => SELECT * FROM world.city where CountryCode='TUR' and District='Ankara';
			//List<City> cities = session.createQuery("from City c where c.countryCode='TUR' AND c.district='Ankara' ").getResultList(); 
			
			//List<City> cities = session.createQuery("from City c where c.name LIKE '%kar%' ").getResultList(); // kar iceren nekadar name varsa
			//List<City> cities = session.createQuery("from City c  ORDER BY c.name").getResultList();// default ascending
			
			/*List<String> countryCodes = session.createQuery("select c.countryCode from City c GROUP BY c.countryCode").getResultList();
			
			for(String countryCode:countryCodes) //cities icin (City city:cities) olacak
			{
				System.out.println(countryCode); // cities icin (city.getName())
			}*/ 
			
			//================================================================================================================
			/*
			 // veri ekleme insert
			City city=new City();
			city.setName("Kastamonu 37");
			city.setCountryCode("TUR");
			city.setDistrict("Karadeniz");
			city.setPopulation(1000000);
			
			session.save(city);
			*/
			//================================================================================================================
			//UPDATE
			City city=session.get(City.class, 4080);// 4080. sehiri getir
			city.setPopulation(50000); // population update 
			//System.out.println(city.getName()); kastamonu 37 test
			session.save(city);
			session.getTransaction().commit();
			System.out.println("guncellendi yeni nufus :"+city.getPopulation());
		}finally
		{
			factory.close();
		}
	}

}
