import React from 'react';
import Footer from '../components/Footer';
import GreetingText from '../components/GreetingText';
import ShowEventCategories from '../components/ShowEventCategories';
import SearchBar from '../components/SearchBar';

export default function Home() {
    return (
      <>
      <div className='background'>
        <div className='b-body'>
          <GreetingText />
          <SearchBar searchParam='' events={[]} />
        </div>
      </div>
      <ShowEventCategories />
      <Footer />
    </>
    )
}

