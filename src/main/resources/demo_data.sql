create function generate_demo_data() returns void
  language plpgsql
as
$$
DECLARE
  state_data TEXT[] := ARRAY[
    'Alabama|AL', 'Alaska|AK', 'Arizona|AZ', 'Arkansas|AR', 'California|CA',
    'Colorado|CO', 'Connecticut|CT', 'Delaware|DE', 'Florida|FL', 'Georgia|GA',
    'Hawaii|HI', 'Idaho|ID', 'Illinois|IL', 'Indiana|IN', 'Iowa|IA',
    'Kansas|KS', 'Kentucky|KY', 'Louisiana|LA', 'Maine|ME', 'Maryland|MD',
    'Massachusetts|MA', 'Michigan|MI', 'Minnesota|MN', 'Mississippi|MS',
    'Missouri|MO', 'Montana|MT', 'Nebraska|NE', 'Nevada|NV', 'New Hampshire|NH',
    'New Jersey|NJ', 'New Mexico|NM', 'New York|NY', 'North Carolina|NC',
    'North Dakota|ND', 'Ohio|OH', 'Oklahoma|OK', 'Oregon|OR', 'Pennsylvania|PA',
    'Rhode Island|RI', 'South Carolina|SC', 'South Dakota|SD', 'Tennessee|TN',
    'Texas|TX', 'Utah|UT', 'Vermont|VT', 'Virginia|VA', 'Washington|WA',
    'West Virginia|WV', 'Wisconsin|WI', 'Wyoming|WY'
    ];
  state_info TEXT;
  state_name TEXT;
  state_abr TEXT;
  jurisdiction_id TEXT;
  question_ids TEXT[];
  question_id TEXT;
BEGIN
  -- Clear existing data
  TRUNCATE TABLE jurisdictions.jurisdictions CASCADE;
  TRUNCATE TABLE jurisdictions.jurisdiction_links CASCADE;
  TRUNCATE TABLE jurisdictions.jurisdiction_variants CASCADE;
  TRUNCATE TABLE jurisdictions.jurisdiction_notification_checklists CASCADE;
  TRUNCATE TABLE jurisdictions.jurisdiction_plain_statues CASCADE;
  TRUNCATE TABLE jurisdictions.reqs_comparison_questions CASCADE;
  TRUNCATE TABLE jurisdictions.reqs_comparison_answers CASCADE;

  -- Generate data for U.S. states
  FOREACH state_info IN ARRAY state_data LOOP
      state_name := split_part(state_info, '|', 1);
      state_abr := split_part(state_info, '|', 2);

      -- Insert into jurisdictions
      INSERT INTO jurisdictions.jurisdictions (id, state, state_abr, state_board_url, renewal_page_url)
      VALUES (
               cast(gen_random_uuid() as TEXT),
               state_name,
               state_abr,
               'https://' || lower(state_name) || '.state.board.example.com',
               'https://' || lower(state_name) || '.renewal.example.com'
             )
      RETURNING id INTO jurisdiction_id;

      -- Insert into jurisdiction_links
      INSERT INTO jurisdictions.jurisdiction_links (id, jurisdiction_id, url, position)
      VALUES
        (cast(gen_random_uuid() as TEXT), jurisdiction_id, 'https://example.com/' || lower(state_name) || '/link1', 1),
        (cast(gen_random_uuid() as TEXT), jurisdiction_id, 'https://example.com/' || lower(state_name) || '/link2', 2);

      -- Insert into jurisdiction_variants
      INSERT INTO jurisdictions.jurisdiction_variants (id, jurisdiction_id, variant)
      VALUES
        (cast(gen_random_uuid() as TEXT), jurisdiction_id, state_name || ' Variant 1'),
        (cast(gen_random_uuid() as TEXT), jurisdiction_id, state_name || ' Variant 2');

      -- Insert into jurisdiction_notification_checklists
      INSERT INTO jurisdictions.jurisdiction_notification_checklists (id, jurisdiction_id, text)
      VALUES
        (cast(gen_random_uuid() as TEXT), jurisdiction_id, 'Notification checklist for ' || state_name);

      -- Insert into jurisdiction_plain_statues
      INSERT INTO jurisdictions.jurisdiction_plain_statues (id, jurisdiction_id, content)
      VALUES
        (cast(gen_random_uuid() as TEXT), jurisdiction_id, 'Rich content for ' || state_name || ' statutes');
    END LOOP;

  -- Generate demo comparison questions
  INSERT INTO jurisdictions.reqs_comparison_questions (id, question, position)
  VALUES
    (cast(gen_random_uuid() as TEXT), 'Does this jurisdiction allow online renewals?', 1),
    (cast(gen_random_uuid() as TEXT), 'Is there a fee waiver available?', 2);

  -- Fetch all question IDs into an array
  SELECT ARRAY_AGG(id) INTO question_ids FROM jurisdictions.reqs_comparison_questions;

  -- Link answers to jurisdictions
  FOR jurisdiction_id IN (SELECT id FROM jurisdictions.jurisdictions) LOOP
      FOREACH question_id IN ARRAY question_ids LOOP
          INSERT INTO jurisdictions.reqs_comparison_answers (id, jurisdiction_id, question_id, answer)
          VALUES (
                   cast(gen_random_uuid() as TEXT),
                   jurisdiction_id,
                   question_id,
                   (random() > 0.5) -- Randomly assign TRUE or FALSE
                 );
        END LOOP;
    END LOOP;
END;
$$;

select generate_demo_data()

