import React from 'react';

const ListofPlayers = () => {
  const players = [
    { name: "Virat", score: 88 },
    { name: "Rohit", score: 65 },
    { name: "Gill", score: 72 },
    { name: "SKY", score: 50 },
    { name: "Rahul", score: 80 },
    { name: "Hardik", score: 69 },
    { name: "Jadeja", score: 90 },
    { name: "Bumrah", score: 45 },
    { name: "Shami", score: 77 },
    { name: "Kuldeep", score: 68 },
    { name: "Chahal", score: 60 },
  ];

  const below70 = players.filter(player => player.score < 70);

  return (
    <div>
      <h2>All Players with Scores</h2>
      <ul>{players.map((p, i) => <li key={i}>{p.name}: {p.score}</li>)}</ul>
      <h2>Players with Score Below 70</h2>
      <ul>{below70.map((p, i) => <li key={i}>{p.name} ({p.score})</li>)}</ul>
    </div>
  );
};

export default ListofPlayers;
