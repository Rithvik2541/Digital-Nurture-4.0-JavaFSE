import React, { Component } from 'react';

class CurrencyConvertor extends Component {
  constructor(props) {
    super(props);
    this.state = { rupees: '', euro: '' };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(e) {
    this.setState({ rupees: e.target.value });
  }

  handleSubmit(e) {
    e.preventDefault();
    const conversionRate = 0.011;
    this.setState({
      euro: (this.state.rupees * conversionRate).toFixed(2)
    });
  }

  render() {
    return (
      <div>
        <h2>Currency Convertor</h2>
        <form onSubmit={this.handleSubmit}>
          <input type="number" value={this.state.rupees} onChange={this.handleChange} placeholder="Enter amount in Rupees" />
          <button type="submit">Convert</button>
        </form>
        {this.state.euro && <p>Converted Amount: €{this.state.euro}</p>}
      </div>
    );
  }
}

export default CurrencyConvertor;
