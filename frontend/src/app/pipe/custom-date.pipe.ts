import { DatePipe } from '@angular/common';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'customDate',
    standalone: true
})
export class CustomDatePipe implements PipeTransform {

    transform(value: string | null, format: string = 'medium'): string {
        if (!value) return '';

        // Parse the date directly
        const date = new Date(value);

        // Use built-in DatePipe for formatting
        const datePipe = new DatePipe('en-US');
        return datePipe.transform(date, format) || '';
    }

}
