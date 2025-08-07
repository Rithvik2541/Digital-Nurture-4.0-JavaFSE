import React, { Component } from 'react';
import CurrencyConvertor from './CurrencyConvertor';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { count: 0, message: '' };
  }

  increment = () => {
    this.setState(prev => ({ count: prev.count + 1 }));
    this.sayHello();
  };

  sayHello = () => {
    this.setState({ message: "Hello! Button clicked." });
  };

  decrement = () => {
    this.setState(prev => ({ count: prev.count - 1 }));
  };

  sayWelcome = (msg) => {
    this.setState({ message: msg });
  };

  handleSyntheticEvent = (e) => {
    e.preventDefault();
    this.setState({ message: "I was clicked" });
  };

  render() {
    return (
      <div style={{ padding: '20px' }}>
        <h1>Event Examples App</h1>
        <h2>Counter: {this.state.count}</h2>
        <button onClick={this.increment}>Increment</button>{" "}
        <button onClick={this.decrement}>Decrement</button>
        <br /><br />
        <button onClick={() => this.sayWelcome("Welcome to React!")}>Say Welcome</button>
        <br /><br />
        <button onClick={this.handleSyntheticEvent}>Synthetic Event (onPress)</button>
        <br /><br />
        {this.state.message && <p><strong>Message:</strong> {this.state.message}</p>}
        <hr />
        <CurrencyConvertor />
      </div>
    );
  }
}

export default App;
