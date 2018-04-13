import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {KweetService} from "../../services/kweet.service";
import {Kweet} from "../../models/Kweet";

@Component({
  selector: 'app-kweet-form',
  templateUrl: './kweet-form.component.html',
  styleUrls: ['./kweet-form.component.css']
})
export class KweetFormComponent implements OnInit {
  @Output() onNewKweet = new EventEmitter<Kweet>()

  message: string
  btnDisabled: boolean

  constructor(private kweetService: KweetService) { }

  ngOnInit() {
    this.btnDisabled = false
  }

  onKweetSubmit() {
    if (!this.message) return
    this.btnDisabled = true
    this.kweetService.postKweet(this.message).subscribe(kweet => {
      this.btnDisabled = false
      this.message = ''
      this.onNewKweet.emit(kweet)
    })
  }
}
