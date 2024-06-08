import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';

import enGB from './locales/en-GB.json';

const defaultLanguage = 'en-GB';

export const defaultNamespace = 'default';

export const resources = {
    'en-GB': {
        [defaultNamespace]: enGB
    }
}

i18n.use(initReactI18next).init({
    defaultNS: defaultNamespace,
    ns: [defaultNamespace],
    resources,
    lng: defaultLanguage,
    fallbackLng: defaultLanguage,
    interpolation: {
        escapeValue: false,
    },
});