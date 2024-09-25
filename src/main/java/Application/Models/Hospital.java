package Application.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "hospitals")
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "street", nullable = false)
	private String street;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "state", nullable = false)
	private String state;

	@Column(name = "contact", nullable = false)
	private Long contact;


	@Column(name = "image", columnDefinition = "LONGBLOB", nullable = false)
	private byte[] image; // Hospital image

	@Column(name = "created_at", nullable = false, columnDefinition = "DATETIME", updatable = false)
	private LocalDateTime createdAt;

	// Default constructor
	public Hospital() {
		this.createdAt = LocalDateTime.now(); // Automatically set the current date and time
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	// Constructor with parameters
	public Hospital(Long id, String name, String description, String street, Long contact, String city, String state, byte[] image, LocalDateTime createdAt) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.street = street;
		this.city = city;
		this.state = state;
		this.image = image;
		this.createdAt = createdAt;
		this.contact = contact;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	// Override equals and hashCode
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Hospital)) return false;
		Hospital hospital = (Hospital) o;
		return Objects.equals(id, hospital.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	// Override toString
	@Override
	public String toString() {
		return "Hospital{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", street='" + street + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", createdAt=" + createdAt +
				'}';
	}
}
