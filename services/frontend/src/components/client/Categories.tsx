import {useTranslation} from "react-i18next";

function Categories() {
    
    const { t } = useTranslation();
    
    return (
        <>
            <div className='w-52 flex bg-slate-500'>
                <div>{t('categories')}</div>   
            </div>
        </>
    )
}

export default Categories;
