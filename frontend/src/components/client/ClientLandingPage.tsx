import Categories from './Categories';
import ProductsContainer from './ProductsContainer';

function ClientLandingPage() {
    return (
        <>
            <div className='flex grow p-4 space-x-4'>
                <Categories />
                <ProductsContainer />
            </div>
        </>
    )
}

export default ClientLandingPage;
