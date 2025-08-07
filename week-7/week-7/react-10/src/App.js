import React from 'react';

const heading = React.createElement('h1', {}, 'Office Space Rental App');
const imageURL = "https://via.placeholder.com/400x250?text=Office+Space";
const officeSpaces = [
  { name: "Cyber View", rent: 55000, address: "Sector 62, Noida" },
  { name: "Tech Park", rent: 75000, address: "Whitefield, Bangalore" },
  { name: "Mindspace", rent: 60000, address: "HiTech City, Hyderabad" }
];

function App() {
  return (
    <div style={{ padding: '20px', fontFamily: 'Arial' }}>
      {heading}
      <img src={imageURL} alt="Office Space" style={{ width: '400px', height: '250px', borderRadius: '10px' }} />
      <h2>Available Spaces</h2>
      {officeSpaces.map((office, i) => {
        const rentStyle = {
          color: office.rent < 60000 ? 'red' : office.rent > 60000 ? 'green' : 'orange',
          fontWeight: 'bold'
        };
        return (
          <div key={i} style={{ margin: '15px 0', border: '1px solid #ddd', padding: '10px', borderRadius: '5px' }}>
            <p><strong>Name:</strong> {office.name}</p>
            <p><strong>Address:</strong> {office.address}</p>
            <p><strong>Rent:</strong> <span style={rentStyle}>₹{office.rent}</span></p>
          </div>
        );
      })}
    </div>
  );
}

export default App;
