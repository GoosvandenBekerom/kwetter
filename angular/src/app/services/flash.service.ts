import { Injectable } from '@angular/core';
import { FlashMessagesService } from 'angular2-flash-messages';

@Injectable()
export class FlashService {
  constructor(private flash: FlashMessagesService) { }

  success(message: string, timeout: number = 5000) {
    this.base(message, 'alert-success', timeout)
  }

  warn(message: string, timeout: number = 5000) {
    this.base(message, 'alert-warning', timeout)
  }

  error(message: string, timeout: number = 5000) {
    this.base(message, 'alert-danger', timeout)
  }

  info(message: string, timeout: number = 5000) {
    this.base(message, 'alert-info', timeout)
  }

  private base(message: string, alertClass: string, timeout: number) {
    this.flash.show(message, { cssClass: 'alert ' + alertClass, timeout: timeout })
  }
}
