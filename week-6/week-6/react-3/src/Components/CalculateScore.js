import React from 'react';
import '../Stylesheets/mystyle.css';

function CalculateScore(props) {
  const average = props.total / props.goal;

  return (
    <div className="container">
      <h2>Student Name: {props.name}</h2>
      <p>School: {props.school}</p>
      <p>Total Marks: {props.total}</p>
      <p>Goal: {props.goal}</p>
      <h3>Average Score: {average}</h3>
    </div>
  );
}

export default CalculateScore;