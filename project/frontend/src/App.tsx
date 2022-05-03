import React from 'react';
import Footer from './components/Footer';
import GreetingText from './components/GreetingText';
import Header from './components/Header';
import CategoryDisplay from './components/ShowEventCategories';

export default function App() {
  return (
    <>
      <Header />
      <GreetingText />
      <CategoryDisplay />
      <Footer />
    </>
  );
}