const minLengthValidator = (value, minLength) => {
    return value.length >= minLength;
};

const requiredValidator = value => {
    return value.trim() !== '';
};

// const isInteger = value => {
//     return Number.isInteger(value)
// };

const validate = (value, rules) => {
    let isValid = true;

    for (let rule in rules) {

        switch (rule) {
            case 'minLength': isValid = isValid && minLengthValidator(value, rules[rule]);
                break;

            case 'isRequired': isValid = isValid && requiredValidator(value);
                break;

            // case 'isInteger': isValid = isValid && isInteger(parseInt(value, 10));
            //     break;

            default: isValid = true;
        }

    }

    return isValid;
};

export default validate;
