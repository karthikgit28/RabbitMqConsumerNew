package com.test.rabbitmq.consumer.rabbitmqconsumertest.consumer.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
	
		//Will be formated in name specified in jsonproprty
		@JsonProperty("name_emp")
		private String name;
		
		private String emp_id;
		
		private Date dateOfBirth;
		
		private String type;
		
		public Employee() {
			
		}

		public Employee(String name, String emp_id, Date dateOfBirth) {
			super();
			this.name = name;
			this.emp_id = emp_id;
			this.dateOfBirth = dateOfBirth;
		}
		
		public Employee(String name, String emp_id, Date dateOfBirth,String type) {
			super();
			this.name = name;
			this.emp_id = emp_id;
			this.dateOfBirth = dateOfBirth;
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmp_id() {
			return emp_id;
		}

		public void setEmp_id(String emp_id) {
			this.emp_id = emp_id;
		}

		public Date getDateOfBirth() {
			return dateOfBirth;
		}

		public void setDateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		
		

}
