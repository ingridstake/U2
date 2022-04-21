import React from 'react';
import Footer from './components/Footer';
import GreetingText from './components/GreetingText';
import Header from './components/Header';
import JsonDataDisplay from './components/ShowEvents';

export default function App() {
  return (
    <>
      <Header />
      <GreetingText />
      <JsonDataDisplay />
      <Footer />
    </>
  );
}