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

  constructor(private kweetService: KweetService) { }

  ngOnInit() {}

  onKweetSubmit() {
    if (!this.message) return
    this.kweetService.postKweet(this.message).subscribe(this.onNewKweet)
    this.message = ''
  }
}
